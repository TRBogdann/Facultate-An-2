
def grayToNum(v):
    num = 0
    for i in range(len(v)-1, -1, -1):
        if i != 0:
            c = v[i] ^ v[i-1]
        else:
            c = v[i]
        num += 2**(len(v)-1-i)*c
    return num


def binToNum(v):
    num = 0
    for i in range(0, len(v)):
        num += 2**i * v[len(v) - i - 1]
    return num
