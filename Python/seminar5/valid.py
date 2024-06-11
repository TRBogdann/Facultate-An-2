def isIdentity(v):
    return 1 == 1


def isValid1(v):
    return v[0] + v[1] + v[2] < 10


def isValid5(v):
    sum = 0
    for it in v:
        sum += it
    return sum % 2 == 1


def isValid7(v):
    return v[5] % 2 == 1


def isValid8(v):
    sum = 0
    for it in v:
        sum += it
    return sum >= 0


def isValid13(v):
    sum = 0
    for it in v:
        sum += it
    return sum <= 10


def isValid15(v):
    sum = 0
    for it in v:
        sum += it
    return sum >= 5


def isValid17(v):
    return v[len(v) - 1] % 2 == 0


def isValid18(v):
    return v[0] + v[1] + v[2] + v[3] >= v[4] + v[5] + v[6] + v[7]


def isValid19(v):
    mx = int(len(v)/2)
    for i in range(0, mx):
        if v[i] == 1:
            return 0
    return 1


def isValid20(v):
    sum = 0
    for it in v:
        sum += it
    return sum < 10
