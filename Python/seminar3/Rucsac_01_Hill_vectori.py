import numpy as np
import matplotlib.pyplot as graphic

#problema 0-1 a rucsacului
#reprezentare: vector de biti (numere 0,1)

#verificarea fezabilitatii si calcului valorii
def ok(c,val,cost,CMax):
    valt=np.dot(c,val)
    costt=np.dot(c,cost)
    return costt<=CMax,valt

#calculul vecinilor unui punct curent si al calitatilor lor
def calcul_vecini(c,val,cost,CMax):
    n=len(c)
    vecini=np.zeros(0,dtype="int")
    calitati=np.zeros(0,dtype="float")
    for i in range(n):
        y=c.copy()
        y[i]=not c[i]
        admisibil,valt=ok(y,val,cost,CMax)
        if admisibil:
            vecini=np.append(vecini,y)
            calitati=np.append(calitati,valt)
    vecini=np.reshape(vecini,[int(len(vecini)/n),n])
    return vecini,calitati


#hill climbing
def HC(fis_c,fis_val,N_incercari,CMax):
    cost=np.genfromtxt(fis_c)
    val = np.genfromtxt(fis_val)
    n=len(cost)
    solutii=np.zeros([N_incercari,n],dtype="int")
    calitati=np.zeros(N_incercari,dtype="float")
    for t in range(N_incercari):
        #cautarea unui optim cu punct de plecare c - aleator, dar fezabil
        # generarea unui punct initial
        fezabil = False
        while not fezabil:
            c = np.random.randint(0, 2, n)
            fezabil, valoare = ok(c, val, cost, CMax)
        gata=False
        while not gata:
            vecini,valori=calcul_vecini(c,val,cost,CMax)
            #cel mai bun vecin, daca exista
            if len(valori):
                poz=np.argmax(valori)
                if valori[poz]>valoare:
                    c=vecini[poz].copy()
                    valoare=valori[poz]
                else:
                    gata=True
            else:
                gata=True
        solutii[t]=c.copy()
        calitati[t]=valoare
    #cea mai buna solutie
    poz_best=np.argmax(calitati)
    sol_calculata=solutii[poz_best]
    val_calculata=calitati[poz_best]
    print("Valoarea maxima:",val_calculata)
    print("Solutia calculata:",sol_calculata)
    return sol_calculata,val_calculata,solutii,calitati

#import Rucsac_01_Hill_vectori as r1
#sol,val,P,C=r1.HC("cost.txt","valoare.txt",30,50) #- max 81
#sol,val,P,C=r1.HC("cost1.txt","valoare1.txt",90,50) #- max 108
#sol,val,P,C=r1.HC("cost2.txt","valoare2.txt",500,56.6) #- max 128.2

