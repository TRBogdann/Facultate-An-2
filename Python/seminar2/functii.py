#sirul lui Fibonacci
# fib[0]=0, fib[1]=1, fib[n]=fib[n-1]+fib[n-2]

#recursiv - doar termenul n
def Fib(n):
    rez=0
    if n==1:
        rez=1
    elif n>1:
        rez=Fib(n-1)+Fib(n-2)
    return rez

#vectori - nerecursiv
import numpy as np

def Fib1(n):
    fib = np.zeros(n+1 , dtype="int")
    fib[1]=1
    for i in range(2,n+1):
        fib[i]=fib[i-2]+fib[i-1]
    return fib

#liste - nerecursvi
def Fib2(n):
    fib=[0,1]
    for i in range(2,n+1):
        fib.append(fib[i-2]+fib[i-1])
        # fib=fib+[fib[i-2]+fib[i-1]]
    return fib


if __name__=="__main__":
    n=int(input("n="))
    sir_F=Fib1(n)
    print("Sirul lui Fibonacci ",sir_F)
