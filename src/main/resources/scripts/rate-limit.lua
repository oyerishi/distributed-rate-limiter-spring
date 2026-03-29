-- 1. Get the arguments and FORCE them to be numbers
local capacity = tonumber(ARGV[1])
local refill_rate = tonumber(ARGV[2])
local now = tonumber(ARGV[3])

-- 2. Fetch the current data from the Hash
local data = redis.call('HMGET', KEYS[1], 'tokens', 'last_refill')
local tokens = tonumber(data[1])
local last_refill = tonumber(data[2])

-- 3. If it's a first-time user, initialize them
if tokens == nil then
    tokens = capacity
    last_refill = now
else
    -- 4. Calculate recovery (The Math)
    local time_passed = now - last_refill
    tokens = math.min(capacity, tokens + (time_passed * refill_rate))
end

-- 5. Logic: Can they pass?
if tokens >= 1 then
    tokens = tokens - 1
    redis.call('HMSET', KEYS[1], 'tokens', tokens, 'last_refill', now)
    redis.call('EXPIRE', KEYS[1], 3600)
    return 1 -- ALLOWED
else
    return 0 -- BLOCKED
end