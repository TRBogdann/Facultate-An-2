
namespace Seminar
{

    internal class Animal:ICloneable,IComparable
    {

         public int Varsta
        {
            get{return varsta;}
            set{if(value>0)this.varsta=value;
                 else  this.varsta=0;
                 }
        }

        public string Nume
        {
            get{return nume;}
            set{this.nume=value;}
        }

        public float Greutate
        {
            get{return greutate;}
            set{this.greutate=value;}
        }

        public Animal()
        {
            this.nume="";
            varsta=0;
            greutate=0;
        }

        public Animal(string nume,int varsta,float greutate)
        {
            this.nume=nume;
            this.varsta=varsta;
            this.greutate=greutate;
        }

        object ICloneable.Clone()
        {
            return this.MemberwiseClone();
        }

        public Animal Clone()
        {
            return (Animal)(((ICloneable)this).Clone());
        }

        public override string ToString()
        {
            return "Animalul este un " + nume +" cu varsta de "+varsta+" ani "+
            "si greutatea de "+greutate;
        }
        public int CompareTo(object obj)
        {
            Animal nou=(Animal)(obj);
            if(this.greutate>nou.greutate)
                return 1;
            if(this.greutate<nou.greutate)
                return -1;

            return string.Compare(this.nume,nou.nume);
        }
    
        private int varsta;
        private String nume;
        private float greutate;


    }

        internal class Zoo:ICloneable
    {

        private string denumire;
        private string adresa;
        private  List<Animal> lista =new List<Animal>();

        public string Denumiere
        {
            get{return denumire;}
            set{this.denumire=value;}
        }
               public string Adresa
        {
            get{return adresa;}
            set{this.adresa=value;}
        }
             public List<Animal>Lista
        {
            get{return lista;}
            set{this.lista=value;}
        }

        object ICloneable.Clone()
        {
            return this.MemberwiseClone();
        }

        public Zoo Clone()
        {
            Zoo nou = (Zoo)(((ICloneable)this).Clone());
            List<Animal> copie=new List<Animal>();
            foreach(Animal a in lista)
            {   
                copie.Add(a.Clone());
            }   
            nou.lista = copie;
            return nou;
        }
    }

    class Pinguin:Animal
    {

        public string Specie
        {
            get{return specie;}
            set{this.specie=value;}
        }

        public Pinguin():base("Pinguin",0,0)
        {
            this.specie="";
        }

        public Pinguin(string specie,int varsta,int greutate):base("Pinguin",varsta,greutate)
        {
            this.specie=specie;
        }

        public override string ToString()
        {
            return base.ToString()+" Este specia " + specie;
        }
        private string specie;
    }

    class Vaccin:ICloneable
    {
        public Vaccin(DateOnly data,string nume)
        {
            this.data=data;
            this.nume=nume;
        }

        object ICloneable.Clone()
        {
            return this.MemberwiseClone();
        }

        Vaccin Clone()
        {
            Vaccin nou = (Vaccin)(((ICloneable)this).Clone());
            DateOnly dat=new DateOnly(this.data.Year,this.data.Month,this.data.Day);
            nou.data=dat;
            nou.nume=this.nume;
            return nou;
        }

        public DateOnly data;
        public string nume;
    }
    class Caine:Animal
    {
        private List<DateTime> vaccinuri;
    }

    class Program
    {
        static void Main(String[] args)
        {
            Animal a1 = new Animal("Caine",10,11);
            Animal a2 = new Animal("Pisica",2,3);
            Animal a3 = new Animal("Soarece",1,1);
            Animal a4 = new Pinguin("Gentoo",5,40);

            Zoo z1=new Zoo();
            z1.Lista.Add(a1.Clone());
            z1.Lista.Add(a2.Clone());
            z1.Lista.Add(a3.Clone());
            z1.Lista.Add(a4.Clone());

            a3.Nume="Hamster";
            Zoo z2=z1.Clone();
            
            

            foreach(Animal a in z1.Lista )
            {
                Console.Out.WriteLine(a);
            }

                

            foreach(Animal a in z2.Lista )
            {
                Console.Out.WriteLine(a);
            }

        }   
    }
}