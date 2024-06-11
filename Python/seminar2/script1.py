print("Hello!")

#citirea unei date de la tastatura
#implicit - tipul str
n=input('Dati n:')
print(type(n))
#pentru a citi o data int
n=int(input('Dati n:'))
print(type(n))

#pentru a citi o data float
a=float(input("valoarea lui a:"))

print("a=",a)
print("n=",n)

#citire vector din fisier text
import numpy as np
v=np.genfromtxt("vector.txt",int)
print("v=",v)

#salvare (scriere) vector intr-un fis text - implicit
np.savetxt("fis1.txt",v)

#salvare (scriere) vector intr-un fis text - tip element int int
np.savetxt("fis2.txt",v,"%i")

#calculul dimensiunii
dim=len(v)
#dim=v.size
#dim=v.shape[0]

#verificarea paritatii dimensiunii
if dim%2==0:
    print("Dimensiune para")
else:
    print("Dimensiune impara")

#produsulu elementelor pozitive
p=1
exista=0
#exista=False
for i in range(dim):
    if v[i]>0:
        p=p*v[i]
        exista=1
        #exista=True

if exista:
    print("Produsul elementelor pozitive",p)
else:
    print("Nu exista elemente pozitive")


#citire matrice din fisier text
a=np.genfromtxt("matrice.txt",int)
print("a=",a)

#salvare (scriere) matrice intr-un fis text - tip element int int
np.savetxt("fis3.txt",a,"%i")

#calculul dimensiunii
lin,col=a.shape


#afisare dimensiuni
print(lin, " linii si ",col," coloane")
#produsulu elementelor pozitive
p=1
exista=0
for i in range(lin):
    for j in range(col):
        if a[i,j]>0:
            p*=a[i,j]
            exista=1
if exista:
    print("Produsul elementelor pozitive",p)
else:
    print("Nu exista elemente pozitive")


