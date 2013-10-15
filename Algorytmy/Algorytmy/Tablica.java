package Algorytmy;

import java.util.Arrays;

class Tablica{
	int[] tab;
	int[] temp;
	int[] tempZlicz;

	/*konstruktory*/
	public Tablica(){}

	public Tablica(int wielkosc){
		tab = new int[wielkosc];
		temp = new int[wielkosc];
	}

	/*wype³nienie tablicy pseudolosowymi liczbami*/
	public void wypelnij(){
		for(int i=0; i<tab.length; i++)
			tab[i]=(int)(Math.random()*100);
	}

	/*wyswietlanie tablicy*/
	public void wyswietl(){
		for(int i=0; i<tab.length; i++)
			System.out.print(tab[i]+"  ");
		System.out.print("\n");
	}

	/*sortowanie funkcj¹ sort*/
	public void arraySort(){
		Arrays.sort(tab);
	}

	/*sortowanie b¹belkowe*/
	public void sortBabel(){
		int temp;
		for(int i=0; i<tab.length; i++)
			for(int j=i+1; j<tab.length; j++){
				System.out.println(i+"  "+j);
				if(tab[i]>tab[j]){
					//split
					temp=tab[i];
					tab[i]=tab[j];
					tab[j]=temp;
				}
			}
	}

	/*sortowanie przez wstawianie*/
	public void sortWstaw(){
		int wartosc;
		int j;
		for(int i=0; i<tab.length; ++i){
			wartosc=tab[i];
			for(j=i-1; j>=0 && tab[j]>wartosc; --j)
				tab[j+1]=tab[j];
			tab[j+1]=wartosc;
		}
	}

	/*sortowanie przez wstawianie od koñca*/
	public void sortWstaw2(){
		int x, i;
		for(int j=tab.length-1; j>=0; j--){
			x=tab[j];
			i=j+1;
			while(i<tab.length && x>tab[i]){
				tab[i-1]=tab[i];
				i++;
			}
			tab[i-1]=x;
		}
	}

	/*sortowanie przez scalanie*/
	public void sortScal(int i_p, int i_k){
		int i_s, i1, i2, i;

		i_s=(i_p+i_k+1)/2;
		if(i_s-i_p>1) sortScal(i_p, i_s-1);
		if(i_k-i_s>0) sortScal(i_s, i_k);
		i1=i_p;
		i2=i_s;
		for(i=i_p; i<=i_k; i++)
			temp[i]=((i1==i_s) || ((i2<=i_k)&&(tab[i1]>tab[i2]))) ? tab[i2++]:tab[i1++];
		for(i=i_p; i<=i_k; i++)
			tab[i]=temp[i];
	}

	/*sortowanie przez zliczanie*/
	public void sortZlicz(){
		int i,k,c;
		int max=0;
		for(int p=0; p<tab.length; p++)
			if(tab[p]>max)
				max=tab[p];
		tempZlicz= new int[max];
		for (i=0;i<tempZlicz.length;i++) 
			tempZlicz[i]=0;//zerowanie tablicy pomocniczej
		for (i=0;i<tab.length;i++) 
			tempZlicz[tab[i]]++; //pobieranie i-tego wyrazu z tablicy i
		//zwiekszanie i-tego wyrazu tablicy pomocniczej
		c=1;
		for (i=0;i<tab.length;i++) {
			if (temp[i]>0) 
				for (k=1;k<tempZlicz[i]+1;k++) {//tworzenie tablicy wynikowej
					tab[c]=i;
					c++;
				}
		}
	}

	/*sortowanie metod¹ pozycyjn¹*/
	public void sortPozycyjne(){
        int a, b, x;
        	for(int i=0; i<2; i++){//rz¹d
            		for (int j=0; j<tab.length-1; j++){
                		for (int k=0; j<tab.length-1-j; k++){
                    			a = (tab[k] / (int)Math.pow(10,i) )%10;
                    			b= (tab[k] / (int)Math.pow(10,i) )%10;
                    			if (a > b){  //sortBabel
                        			x=tab[k+1];
                        			tab[k+1]=tab[k];
                        			tab[k]=x;
                   			}
                		}
            		}
        	}   
    	}

	/*sortowanie wybór*/
	public void sortWybor(){ // wyszukiwanie najmniejszego elementu i zamiana z i-t¹ pozycj¹
       
        int poz = 1;
        int tmp,k;
        	for(int i=0;i<tab.length-1;i++){
             		int min = 100;
            		for(k=i;k<tab.length;k++){
                		if(tab[k]<min){ // sprawdz czy dany element jest mniejszy od aktualnego
                    		 min=tab[k];
                   		 poz=k;
                		}
            		}
	    //split
            tmp=tab[i]; 
            tab[i]=min; 
            tab[poz]=tmp ; 
        	}
	}
	

	/*sortowanie quickSort*/
	public void quicksort(int x, int y) {
		int i,j,v,temp;
		i=x;
		j=y;
		v=tab[(x+y) / 2];
		do {
			while (tab[i]<v) 
				i++;
			while (v<tab[j]) 
				j--;
			if (i<=j) {
				temp=tab[i];
				tab[i]=tab[j];
				tab[j]=temp;
				i++;
				j--;
			}
		}
		while (i<=j);
		if (x<j) 
			quicksort(x,j);
		if (i<y) 
			quicksort(i,y);
	}

	/*sortowanie przez kopcowanie*/
	private void sortKopiec(int array[], int n){
    	int i, o;
        int lChild, rChild, mChild, root, temp;
    		root = (n-1)/2;

    		for(o = root; o >= 0; o--){
      			for(i=root;i>=0;i--){
        		lChild = (2*i)+1;
                	rChild = (2*i)+2;
        			if((lChild <= n) && (rChild <= n)){
          				if(array[rChild] >= array[lChild])
            					mChild = rChild;
          				else
            					mChild = lChild;
        			}
                		else{
          			if(rChild > n)
            				mChild = lChild;
          			else
            				mChild = rChild;
        			}

        			if(array[i] < array[mChild]){
         				temp = array[i];
          				array[i] = array[mChild];
                    			array[mChild] = temp;
        			}
      			}
    		}
    	temp = array[0];
    	array[0] = array[n];
    	array[n] = temp;
    	return;
  	}

  	public void sortKopiec(){
      		for(int i=tab.length; i>1; i--)
        	sortKopiec(tab, i - 1);
    	}
	
}
