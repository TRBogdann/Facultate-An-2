#include <stdio.h>
#include <string.h>
#include <malloc.h>

typedef struct Prajitura
{
    int prioritate;
    char *denumire;
        float pret;
}Prajitura;


typedef struct MaxHeap
{
    int dimensiune;
    Prajitura* data;
}MaxHeap;

void printPrajitura(Prajitura p)
{
    printf("Prioritate: %d Nume: %s Pret: %.2f\n",p.prioritate,p.denumire,p.pret);
}

Prajitura newPrajitura(int prioritate, const char* denumire, float pret)
{
    Prajitura p;
    p.denumire = (char*)malloc(strlen(denumire)+1);
    strcpy(p.denumire,denumire);
    p.pret = pret;
    p.prioritate = prioritate;

    return p;
}

MaxHeap newMaxHeap(int size)
{
    MaxHeap h;
    h.dimensiune = size;
    h.data = (Prajitura*)malloc(size*sizeof(Prajitura));
    return h;
}

void printHeap(MaxHeap heap)
{
    for(int i = 0; i < heap.dimensiune; i++)
    {
        printPrajitura(heap.data[i]);
    }
}

void reorderElement(MaxHeap *h,int index)
{
    if(h->dimensiune == 0)
        return;
    int st = 2 * index+1;
    int dr = 2* index + 2;
    int pozMax = index;
    if(dr < h->dimensiune && h->data[dr].prioritate>h->data[pozMax].prioritate)
    {
        pozMax = dr;
    }

    if(st < h->dimensiune && h->data[st].prioritate>h->data[pozMax].prioritate)
    {
        pozMax = st;
    }
    if(pozMax != index)
    {
        Prajitura aux = (*h).data[index];
        (*h).data[index]=(*h).data[pozMax];
        (*h).data[pozMax] = aux;
        if(pozMax <= h->dimensiune/2-1)
        {
            reorderElement(h,pozMax);
        }
    }
    
}

void reorderHeap(MaxHeap *h)
{
    for(int i = (*h).dimensiune/2-1 ; i>=0 ;i--)
    {
        reorderElement(h,i);
    }
}

void deletePrajitura(Prajitura p)
{
    if(p.denumire == NULL) return;
    free(p.denumire);
    p.denumire = 0;
}

Prajitura pop(MaxHeap *h)
{
    Prajitura p;
    if(h->dimensiune==0){
        p.prioritate=-1;
        return p;
    }
    p.denumire = (char*)malloc(strlen(h->data[0].denumire)+1);
    strcpy(p.denumire,h->data[0].denumire);
    p.pret = h->data[0].pret;
    p.prioritate = h->data[0].prioritate;
    Prajitura aux = h->data[0];
    h->data[0] = h->data[h->dimensiune-1];
    h->data[h->dimensiune-1] = aux;
    deletePrajitura(h->data[h->dimensiune-1]);
    h->dimensiune --;
    reorderHeap(h);
    
    return p;

}


char isEmpty(MaxHeap h)
{
    return h.dimensiune > 0;
}

void deleteHeap(MaxHeap *h)
{
    for(int i = 0;i<h->dimensiune;i++)
    {
        deletePrajitura(h->data[i]);
    }
    free((*h).data);
    h->dimensiune = 0;
    h->data = 0;
}

int main()
{
    MaxHeap heap = newMaxHeap(6);
    
    heap.data[0] = newPrajitura(10,"Amandina",2.7f);
    heap.data[1] = newPrajitura(11,"LavaCake",10.5f);
    heap.data[2] = newPrajitura(15,"Ecler",3.4f);
    heap.data[3] = newPrajitura(17,"Savarina",8.7f);
    heap.data[4] = newPrajitura(20,"Profiterol",15.9f);
    heap.data[5] = newPrajitura(12,"Macarone",3.7f);
    reorderHeap(&heap);
    printHeap(heap);
    while(!isEmpty(heap))
    {
        printPrajitura(pop(&heap));
    }
    deleteHeap(&heap);
    return 0;
}