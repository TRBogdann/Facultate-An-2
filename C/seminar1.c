#include <stdio.h>
#include <malloc.h>

int sum(int a ,int b)
{
    return a+b;
}

void citireVector(int** vector,int* dimensiune)
{
    printf("Dimensiune: ");
    scanf("%d",dimensiune); 
    
    *vector=(int*)malloc((*dimensiune)*(sizeof(int)));
     for(int i=0;i<*dimensiune;i++)
     {
        printf("elemet %d = ",i+1);
        scanf("%d",&(*vector)[i]);
     }
}

void afisareVector(int* vector,int dimensiune)
{
    printf("\n");
    for(int i=0;i<dimensiune;i++)
    {
        printf("%d ",vector[i]);
    }
}

void stergereVector(int **vector)
{
    free(*vector);
    *vector=0;
}


void myswap(int *a,int *b)
{
    int aux=*a;
    *a=*b;
    *b=aux;
}

typedef struct 
{
int (*fun)(int,int);
void (*swap)(int*,int*);
} Test;

int main()
{
    int a;
    int nr2=0;
    float b;
    char c;
    char *str=(char*)malloc(10*sizeof(char));
    char str2[8];
    Test t;
    t.fun=sum;
    t.swap=myswap;

    int *vector;
    int dimensiune;



    unsigned int len=10;
    getline(&str,(size_t*)&len,stdin);
    scanf("%d",&a);
    scanf("%f",&b);
    getc(stdin);
    scanf("%c",&c);

    for(int i=0;i<8;i++)
    {
        str2[i]= 97+i; // 'a' = 97
    }

    str2[7]='\n';

    t.swap(&a,&nr2);

    printf("%s",str2);
    printf("%s",str);
    printf("%d\n",a);
    printf("%.2f\n",b);
    printf("%c\n",c);
    printf("%d\n",c);

    free(str);


    printf("\n");
    
    citireVector(&vector,&dimensiune);
    afisareVector(vector,dimensiune);
    stergereVector(&vector);

    if(vector==0)
    {
        printf("\nVECTOR STERS!");
    }


    return 0;
}