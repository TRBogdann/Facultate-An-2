def sortDesc(li, i):
    for j in range(i, len(li)-1):
        for k in range(j+1, len(li)):
            if li[j] > li[k]:
                (li[k], li[j]) = (li[j], li[k])


def sortAsc(li, i):
    for j in range(i, len(li)-1):
        for k in range(j+1, len(li)):
            if li[j] < li[k]:
                (li[k], li[j]) = (li[j], li[k])

def increment(p):
    nxt = p.copy()
    i = len(nxt) - 2
    while i >= 0 and nxt[i+1] < nxt[i]:
        i -= 1
    j = len(nxt) - 1
    while nxt[j] < nxt[i]:
        j -= 1
    if i >= 0:
        (nxt[i], nxt[j]) = (nxt[j], nxt[i])
        sortDesc(nxt, i+1)
    return nxt


def decrease(p):
    nxt = p.copy()
    i = len(nxt) - 2
    while i >= 0 and nxt[i+1] > nxt[i]:
        i -= 1
    j = len(nxt) - 1
    while nxt[j] > nxt[i]:
        j -= 1
    if i >= 0:
        (nxt[i], nxt[j]) = (nxt[j], nxt[i])
        sortAsc(nxt, i+1)
    return nxt


def factorial(n):
    if n < 2:
        return 1
    else:
        return n * factorial(n-1)


def nthPermutation(n, maxN):
    p = []
    for i in range(1, maxN+1):
        p.append(i)
    for i in range(0, n):
        p = increment(p)
    return p

