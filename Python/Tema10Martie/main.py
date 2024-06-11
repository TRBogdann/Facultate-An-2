import numpy as np
import pylab as pl
from matplotlib import pyplot as plt
def problema1(mat):
    count = 0
    for line in mat:
        asc = 1
        for i in range(0, len(line)-1):
            if line[i+1] < line[i]:
                asc = 0
        if asc:
            count += 1
    return count





def problema2(mat):
    count = 0
    for line in mat:
        minl = line[0]
        for i in range(1, len(line)):
            if line[i] < minl:
                minl = line[i]
        if minl == 5:
            count += 1
    return count


def bubbleSort(li: list):
    for i in range(0, len(li)-1):
        for j in range(i+1, len(li)):
            if li[i] > li[j]:
                (li[j], li[i]) = (li[i], li[j])


def problema3(mat):
    for it in mat:
        bubbleSort(it)


def problema4(mat):
    for j in range(0, len(mat[0])):
        for i in range(1, len(mat)):
            k = 0
            while k < i:
                if mat[i][j] < mat[k][j]:
                    e = mat[i][j]
                    for l in range(k+1, i+1)[::-1]:
                        mat[l][j] = mat[l-1][j]
                    mat[k][j] = e
                    k = len(mat) + 1

                k += 1


def problema5(a, b):
    if a == b:
        return a
    if a > b:
        return problema5(a-b, b)
    return problema5(a, b-a)

#problema 6
def transpose(mat):
    res = []
    for i in range(0, len(mat)):
        li = []
        for j in range(0, len(mat)):
            li.append(mat[j][i])
        res.append(li)
    return res


def addMat(mat1, mat2):
    res = []
    for i in range(0, len(mat1)):
        li = []
        for j in range(0, len(mat1)):
            li.append(mat1[i][j]+mat2[i][j])
        res.append(li)
    return res


def mulMat(mat1, mat2):
    res = []
    for i in range(0, len(mat1)):
        li = []
        for j in range(0, len(mat1)):
            s = 0
            for k in range(0, len(mat1)):
                s += mat1[i][j] * mat2[k][j]
            li.append(s)
        res.append(li)
    return res


def powMat(mat, n):
    temp = mat
    for i in range(1, n):
        temp = mulMat(temp, mat)
    return temp


#Problema 7


def deleteElement(li: list, pos):
    for i in range(pos, len(li)-1):
        li[i] = li[i+1]
    li.pop()


def insertElement(li: list, pos, element):
    li.append(0)
    for i in range(pos+1, len(li)):
        li[i] = li[i-1]
    li[pos] = element

def problema7(li):
    for i in range(1, len(li)):
        k = 0
        while k < i:
            if li[i] < li[k]:
                e = li[i]
                for l in range(k+1, i+1)[::-1]:
                    li[l] = li[l-1]
                li[k] = e
                break
            k += 1


def problema8(li):
    for i in range(0, len(li)):
        if i+1 != li[i]:
            return 0
    return 1


def quality9(mat):
    li = []
    for line in mat:
        s = 0
        for it in line:
            s += it
        li.append(s)
    return li


def rankMatrix(mat, qualities):
    for i in range(0, len(mat)-1):
        for j in range(i+1, len(mat)):
            if qualities[i] > qualities[j]:
                (qualities[i], qualities[j]) = (qualities[j], qualities[i])
                (mat[i], mat[j]) = (mat[j], mat[i])


def functieVal(x: float):
    return np.sin(x-2)*np.sin(x-2)-x*np.cos(x)


def codify(number: int, size):
    li = np.zeros(size, int)
    i = size - 1
    while i >= 0 and number > 0:
        li[i] = number % 2
        number /= 2
        i -= 1

    return li


def decodify(li):
    p = len(li) - 1
    number = 0
    for it in li:
        number += it * pow(2, p)
        p -= 1
    return number


def increment(lis):
    li = lis.copy()
    k = 0
    i = len(li)-1
    while k != 1 and i >= 0:
        if li[i] == 0:
            li[i] = 1
            k = 1
        else:
            li[i] = 0
        i -= 1
    return li


def decrease(lis):
    li = lis.copy()
    k = 0
    i = len(li)-1
    while k != 1 and i >= 0:
        if li[i] == 1:
            li[i] = 0
            k = 1
        else:
            li[i] = 1
        i -= 1
    return li


def genPopulatieUniform(minVal: float, maxVal: float, nrOfValues, aprox=False):
    ratio = (maxVal - minVal) / (nrOfValues - 1)
    population = []
    while minVal < maxVal:
        if not aprox:
            population.append(minVal)
        else:
            population.append(int(minVal))
        minVal += ratio
    return population


def calculVecini(current, minValue, maxValue, ratio, func):

    left = current
    right = left
    if current - ratio >= minValue:
        left = current - ratio
    if current + ratio <= maxValue:
        right = current + ratio

    res = current
    if func(left) > func(res):
        res = left
    if func(right) > func(res):
        res = right
    return res


def binaryComp(bin1, bin2):
    for i in range(0, len(bin1))[::-1]:
        if bin1[i] > bin2[i]:
            return -1
        if bin1[i] < bin2[i]:
            return 1
    return 0


def calculVeciniBin(current, func, maxVal):
    left = decrease(current)

    if binaryComp(current, codify(0, len(current))) == 1:
        left = current.copy()

    right = increment(current)
    if binaryComp(current, maxVal) == 1:
        right = current.copy()

    if func(decodify(left)) > func(decodify(current)):
        current = left.copy()
    if func(decodify(right)) > func(decodify(current)):
        current = right.copy()

    return current.copy()



def isDif(list1, list2):
    if len(list1) != len(list2):
        return True
    for i in range(0, len(list1)):
        if list1[i] != list2[i]:
            return True
    return False


def isDifbin(list1, list2):
    if len(list1) != len(list2):
        return True
    for i in range(0, len(list1)):
        for j in range(0, len(list1[i])):
            if list1[i][j] != list2[i][j]:
                return True
    return False



def rank(population, func):
    values = []
    final = population.copy()
    for i in range(0, len(final)-1):
        for j in range(i+1, len(final)):
            if func(final[i]) < func(final[j]):
                (final[i], final[j]) = (final[j], final[i])
    for it in final:
        values.append(func(it))

    return final, values


def hc(initPop: list , func, minVal, maxVal, ratio, it):
    changed = 1
    prev = initPop.copy()
    j = 0
    plt.ion()
    while j < it and changed:
        for i in range(0, len(initPop)):
            initPop[i] = calculVecini(initPop[i], minVal, maxVal, ratio, func)
            changed = isDif(prev, initPop)
            initPop, vals = rank(initPop, functieVal)
            for k in range(3, len(initPop)):
                probability_table = [0.1, 0.9]
                if np.random.choice(range(0, 2), 1, p=probability_table) and prev[k] == initPop[k]:
                    initPop[k] = np.random.randint(1, 2500)

            prev = initPop.copy()

            xs = np.linspace(1, 2500, 3000)
            ys = func(xs)
            pl.plot(xs, ys)
            y = []
            for iter in initPop:
                y.append(func(iter))
            plt.plot(initPop, y, 'o', color='black')
            plt.draw()
            plt.pause(0.001)
            plt.clf()

        j += 1


def hcbin(initPop: list, func, maxVal, it: int):
        changed = 1
        prev = initPop.copy()
        j = 0
        plt.ion()
        while j < it and changed:
            for i in range(0, len(initPop)):
                initPop[i] = calculVeciniBin(initPop[i], func, maxVal)
                changed = isDifbin(prev, initPop)
                prev = initPop.copy()

                xs = np.linspace(1, 2500, 3000)
                ys = func(xs)
                pl.plot(xs, ys)
                y = []
                x = []
                for iter in initPop:
                    y.append(func(decodify(iter)))
                    x.append(decodify(iter))
                plt.plot(x, y, 'o', color='black')
                plt.draw()
                plt.pause(0.001)
                plt.clf()

            j += 1


def problema11():
    population = genPopulatieUniform(1, 2500, 40, aprox=True)
    hc(population, functieVal, 1, 2500, 1, 60)
    rezultat, valori = rank(population, functieVal)
    for i in range(0, len(rezultat)):
        print(f'Rank: {i+1} x:{rezultat[i]} Value:{valori[i]}')


def problema12():
    uncodified = genPopulatieUniform(1, 2500, 40, aprox=True)
    codified = []
    for i in uncodified:
        codified.append(codify(i, 12))
    hcbin(codified, functieVal,  codify(2500, 12), 120)
    uncodified = []
    for it in codified:
        uncodified.append(decodify(it))
    rezultat, valori = rank(uncodified, functieVal)
    for i in range(0, len(rezultat)):
        print(f'Rank: {i+1} x:{rezultat[i]} Value:{valori[i]}')
def test():

    print('\nProblema1:\n')
    mat1 = [[1, 2, 3, 4], [2, 3, 1, 2], [2, 1, 7, 2], [1, 5, 10, 11]]
    print(problema1(mat1))

    print('\nProblema2:\n')
    mat2 = [[1, 4, 2, 5], [5, 6, 7, 8], [6, 7, 123, 5]]
    print(problema2(mat2))

    print('\nProblema3:\n')
    problema3(mat2)
    print(mat2)

    print('\nProblema4:\n')
    for it in mat1:
        print(it)
    print('\n')

    problema4(mat1)

    for it in mat1:
        print(it)

    print('\nProblema5:\n')
    print(problema5(12, 8))

    print('\nProblema6:\n')
    mat61 = [
            [1, 45, 87, 22],
            [2, 1, 1, 4],
            [81, 21, 102, 93],
            [1, 23, 1, 24]
            ]

    mat62 = [[1, 1, 1, 1], [2, 2, 2, 2], [2, 0, 0, 2], [1, 0, 0, 1]]

    print(transpose(mat61))
    print(addMat(mat61, mat62))
    print(mulMat(mat62, mat61))

    print('\nProblema7:\n')
    li7 = [23, 4, 1, 36, 123, 41, 82]
    problema7(li7)
    print(li7)


    print('\nProblema8:\n')
    li81 = [1, 2, 3, 4, 5]
    li82 = [1, 3, 2, 5, 4]

    print(li81)
    print(problema8(li81))

    print(li82)
    print(problema8(li82))

    print('\nProblema9:\n')
    mat9 = np.random.randint(low=0, high=2, size=(20, 7))
    print(mat9)
    print(quality9(mat9))

    print('\nProblema10:\n')
    rankMatrix(mat9, quality9(mat9))
    print(mat9)

    print('\nProblema11:\n')
    problema11()

    print('\nProblema12:\n')
    problema12()
test()

