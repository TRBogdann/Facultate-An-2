from codify import *


def isIdentity(v):
    return 1 == 1


def isValid3(x, pos):
    if pos == 0:
        return -1 <= x <= 1
    if pos == 1:
        return 0 <= x <= 0.2
    if pos == 2:
        return 0 <= x <= 1
    return 0 <= x <= 5


def isValid6(v):
    return grayToNum(v) < 350


def isValid9(v):
    limit = [1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0]
    for i in range(len(v)):
        if v[i] > limit[i]:
            return False
        if v[i] < limit[i]:
            return True
    return True


def isValid11(v):
    limit = [1, 1, 1, 1, 1, 0, 1, 0, 0]
    for i in range(len(v)):
        if v[i] > limit[i]:
            return False
        if v[i] < limit[i]:
            return True
    return True

