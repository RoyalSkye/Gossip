
public class Antientropy1 {  //anti-entropy(push-pull)
	
	public final static int N=100;
	private double data;
	private int flag=-1;  //��ǽ���Ƿ�ͨ�Ź������������е�λ�ã�
	
	public double getData(){
		return this.data;
	}
	public void setData(double data){
		this.data=data;
	}
	public int getFlag(){
		return this.flag;
	}
	public void setFlag(int flag){
		this.flag=flag;
	}
	
	public static void main(String[] args){
		int count=0;  //����ģ��ͨ������
		int length1=1;  //��¼node1�ĳ���
		int test=1;
		double sum=0;
		double average;
		Antientropy1[] node=new Antientropy1[N];  //���н��
		Antientropy1[] node1=new Antientropy1[N];  //�Ѹ�Ⱦ�Ľ��
		for(int i=0;i<N;i++){  //��ʼ��node
			node[i]=new Antientropy1();
			node[i].setData(i+1);
			sum+=node[i].getData();
		}
		average=sum/N;
		node[0].setFlag(0);
		node1[0]=new Antientropy1();
		node1[0].setData(node[0].getData());
		node1[0].setFlag(0);
		do{
			test=1;
			int length=length1;
			for(int i=0;i<length;i++){  //node1�У��Ѹ�Ⱦ�Ľ�㣩�����ѡȡһ��������ͨ��
				int rank=(int)(Math.random()*N);  //[0,99]
				while(rank==node1[i].getFlag()){  //�������Լ�ͨ��
					rank=(int)(Math.random()*N);  //[0,99]
				}
				if(node[rank].getFlag()==-1){  //�����ѡȡ�Ľ��δͨ�Ź�
					node[rank].setFlag(length1);
					node1[length1]=new Antientropy1();
					double temp=(node1[i].getData()+node[rank].getData())/2;
					node1[i].setData(temp);
					node1[length1].setData(temp);
					node1[length1].setFlag(rank);
					length1++;
				}else{  //���ѡȡ�Ľ��ͨ�Ź�
					double temp=(node1[i].getData()+node1[node[rank].getFlag()].getData())/2;
					node1[i].setData(temp);
					node1[node[rank].getFlag()].setData(temp);
				}
			}
		 /*
			˵������Ҫ������ֵ���ϸ����averageʱ�˳�ѭ������ȥ������forѭ������ע��
			
			//for(int i=0;i<length1;i++){  //ͨ��count�ֺ�������ƽ��ֵ
				//System.out.println("node1["+i+"]="+node1[i].getData());
				//if(node1[i].getData()==node1[0].getData()&&node1[i].getData()==average) test*=1;
				//else test*=0;
			//}
			
		*/
			for(int i=0;i<N;i++){  //���н�㶼Ϊ�Ѹ�Ⱦʱ�˳�ѭ��
				if(node[i].getFlag()!=-1) test*=1;
				else test*=0;
			}
			count++;
		}while(test==0);
		System.out.println("N="+N);
		System.out.println("count="+count);
	}
}
