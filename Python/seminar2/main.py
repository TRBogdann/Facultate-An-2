import numpy as np

def mirror(n):
    result = 0
    while n > 0:
        result *= 10
        result += n % 10
        n = int(n / 10)
    return result

def eraseDuplicates(vector):
    newVector=[]
    for iterator in vector:
        if it not in newVector:
            newVector.append(iterator)

    return newVector

def rev(vector):
    list1 = []
    for iterator in vector:
        list1.append(mirror(iterator))
    return list1


mat = [[1, 1, 1], [1, 2, 3], [1, 3, 4]]

vect = np.genfromtxt("vector.txt", int)

produs = 1

for itc in mat:
    for itl in itc:
        if itl % 2 == 0 and itl > 0:
            produs *= itl
print(f'Produs Matrice: {produs}\n')

print("Elemente Vector: ")

for it in vect:
    print(it)

for it in mat:
    with open("matrice.txt", "ab") as f:
        f.write(b'\n')
        np.savetxt(f, it, "%i")

# Np

v = np.random.randint(10, 20, 5)

print(v)
print(rev(v))


eraseDuplicates(v)
