
public class Antientropy {  //anti-entropy(push)
	
	public final static int N=100;  //������
	private double data;  //����������Ϣ
	private int flag=-1;  //��ǽ���Ƿ�ͨ�Ź�(���������е�λ��)
	
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
		Antientropy[] node=new Antientropy[N];  //���н��
		Antientropy[] node1=new Antientropy[N];  //�Ѹ�Ⱦ�Ľ��
		for(int i=0;i<N;i++){  //��ʼ��node
			node[i]=new Antientropy();
			node[i].setData(i+1);
		}
		node[0].setFlag(0);  //��node[0]����Ϊ��ʼ���Ѹ�Ⱦ�Ľ��
		node1[0]=new Antientropy();
		node1[0].setData(node[0].getData());
		node1[0].setFlag(0);
		do{
			test=1;
			int length=length1;  //length(length1)Ϊnode1 �Ѹ�Ⱦ�Ľ������ĳ���
			for(int i=0;i<length;i++){  //node1�У��Ѹ�Ⱦ�Ľ�㣩�����ѡȡһ��������ͨ��
				int rank=(int)(Math.random()*N);  //[0,99]
				while(rank==node1[i].getFlag()){  //�������Լ�ͨ��
					rank=(int)(Math.random()*N);
				}
				if(node[rank].getFlag()==-1){  //�����ѡȡ�Ľ��δͨ�Ź�
					node[rank].setFlag(length1);
					node1[length1]=new Antientropy();
					node1[length1].setData(node1[i].getData());
					node1[length1].setFlag(rank);
					length1++;
				}
			}
			for(int i=0;i<length1;i++){
				if(node1[i].getData()==node1[0].getData()&&node[i].getFlag()!=-1) test*=1;
				else test*=0;
				//System.out.println("node1["+i+"]="+node1[i].getData());
			}
			count++;
		}while(test==0);
		System.out.println("N="+N);
		System.out.println("count="+count);
	}
}
