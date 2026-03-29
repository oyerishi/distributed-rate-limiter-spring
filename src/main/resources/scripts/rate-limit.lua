local bucket = redis.call('HMGET', KEYS[1], 'tokens', 'last_refill')
local tokens = tonumber(bucket[1])
local last_refill = tonumber(bucket[2])

-- If the bucket doesnt exist yet, initialize it
if tokens == nil then
    tokens = tonumber(ARGV[1]) --ARGV[1] is the capacity
    last_refill = tonumber(ARGV[3]) --ARGV[3] is the current timestamp
end

--Math: How much time is passed?
local now = tonumber(ARGV[3])
local time_passed = now - last_refill
local refill_amount = time_passed * ARGV[2]

--Add tokens but dont exceed capacity
tokens = Math.min(tonumber(ARGV[1], tokens+refill_amount))

--check if we can consume 1 token
if tokens>=1 then
    tokens = tokens-1
    redis.call('HMSET', KEYS[1], 'tokens',tokens, 'last_refill', last_refill)
    --set a 1 hour "Self Destruct" so we don't waste RAM
    redis.call('EXPIRE', KEYS[1],3600)
    return 1 --ALLOWED
else
    return 0 -- BLOCKED
end