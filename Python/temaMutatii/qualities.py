import numpy
import numpy as np
from codify import *


def quality1(v):
    count = 0
    for i in range(0, len(v)-1):
        for j in range(i+1, len(v)):
            if v[i] == j + 1 and v[j] == i + 1:
                count += 1
    return count


def quality2(v):
    x = 0
    y = 0
    dim = len(v[0])
    for i in range(0, dim):
        x += 2 ** i * int(v[0][::-1][i])
        y += 2 ** i * int(v[1][::-1][i])
    return y * (np.sin(x-2)**2)


def quality3(v):
    return 1+np.sin(2*v[0]-v[2])+np.power((v[1]*v[3]), 1/3)


def quality4(v):
    return 1+np.sin(2*v[0]-v[2])+np.cos(v[1])


def quality5(v):
    mysum = 0
    for it in v:
        mysum += it
    return mysum


def quality6(v):
    return grayToNum(v)**2


def quality8(v):
    n = len(v)
    count = 0
    for i in range(0, len(v)-1):
        for j in range(i+1, len(v)):
            if abs(v[i]-v[j]) == abs(i - j):
                count += 1
    return int(n*(n-1)/2-count)


def quality9(v):
    return np.sin(binToNum(v)-2)**2


def quality11(v):
    x = binToNum(v)
    return np.sin(x-1)**2-x*np.cos(2*x)

