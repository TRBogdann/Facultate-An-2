def quality1(v):
    return v[0] ** 2 - 2 * v[1] * v[2]


def quality2(v):
    count = 0
    for i in range(0, len(v) - 1):
        if v[i] != v[i + 1]:
            count += 1
    return count


def quality3(v, val):
    sum = 0
    for i in range(0, len(v)):
        sum += v[i] * val[i]
    return sum


def quality4(v):
    count = 0
    for i in range(0, len(v)-1):
        for j in range(i+1, len(v)):
            if (v[i] - v[j]) % 2 == 0:
                count += 1
    return count


def binToNum(v):
    num = 0
    p = 1
    for i in range(0, len(v))[::-1]:
        num += p*v[i]
        p *= 2
    return num


def quality6(v):
    count = 0
    for i in range(0, len(v)-1):
        for j in range(i+1, len(v)):
            if v[i] == j+1 and v[j] == i+1:
                count += 1
    return count


def quality7(v):
    prod = 1
    for it in v:
        prod *= it
    return prod


def quality8(v):
    sum = 0
    for it in v:
        sum += abs(it)
    return sum


def quality9(v):
    count = 0
    for i in range(0, len(v) - 1):
        if v[i] == v[i + 1]:
            count += 1
    return count


def quality11(v):
    return v[3] * (v[0] ** 2) - 2 * v[1] * v[2]


def identityQuality(v):
    return v[len(v)-1]


def quality12(v):
    sum = 0
    for i in range(1, len(v), 2):
        if v[i] == 1:
            sum += 1
    return sum


def quality14(v):
    count  = 0
    for i in range(1, len(v)):
        if v[i] < i+1:
            count += 1
    return count


def quality15(v):
    sum = 0
    for i in range(0, len(v)):
        sum += i*v[i]
    return sum


def quality16(v):
    count = 0
    for i in range(0, len(v)-1):
        if v[i+1] == i+1 and v[i] == i+2:
            count += 1
    return count


def quality18(v):
    return v[0] + v[1] + v[2] + v[3] - v[4] - v[5] - v[6] - v[7]


def quality19(v):
    sum = 0
    for i in range(0, len(v)):
        if v[i] % 2 == 0:
            sum += i
    return sum


def quality20(v):
    p = 1
    for it in v:
        p *= abs(it)
    return p
