import java.util.Scanner;

public class Gossip {  //gossip
	
	public final static int N=100;
	private double data;
	private boolean infected = false;
	private boolean isolated = false;
	
	public Gossip(int i){
		this.data=i;
	}
	
	public double getData(){
		return this.data;
	}
	public void setData(double data){
		this.data=data;
	}
	public boolean isInfected() {
		return infected;
	}
	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	public boolean isisolated() {
		return isolated;
	}
	public void setisolated(boolean isolated) {
		this.isolated = isolated;
	}
	
	public static void main(String[] args){
		Gossip[] nodes1=new Gossip[N];	//�����жϣ��洢��Ϊ��ǰ�ֿ�ʼǰ������״̬
		Gossip[] nodes2=new Gossip[N];  //�����޸ģ��洢�뵱ǰ��ͬ���ĸ����״̬
		int k = 10;  //�Ѹ�Ⱦ����..����Ѹ���ĸ���1.0/K
		int round = 0;  //����
		int infected = 0;  //�Ƿ��Ѹ�Ⱦ
		int isolated = 0;	//�Ƿ��Ѹ���
		System.out.println("There are "+N+" nodes now, please input the amount of infected nodes:");
		Scanner s = new Scanner(System.in);  //�����ʼ�Ѹ�Ⱦ�Ľ����
		infected = s.nextInt();
		for(int i=0;i < N; i++){
			nodes1[i]=new Gossip(i);
			nodes2[i]=new Gossip(i);
			if(i < infected){
				nodes1[i].setInfected(true);
				nodes2[i].setInfected(true);
			}else{
				nodes1[i].setInfected(false);
				nodes2[i].setInfected(false);
			}
		}	
		for(;isolated < infected && infected < N;round++){  //�����н��ȫ��ͨ�Ź������ĳһС���ֽ��δͨ�ţ��������Ѹ���ʱ����ѭ��
			for(int i=0;i<N;i++){
				int random=(int)(Math.random()*N);  //[0,N-1]
				while(random==i){  //�������Լ�ͨ��
					random=(int)(Math.random()*N);
				}
				if(nodes1[i].isInfected()==true&&nodes1[i].isisolated()==false){  //��ǰ����Ѹ�Ⱦ��δ����
					if(nodes1[random].infected==true){  //���ѡȡ�Ľ���Ѹ�Ⱦ����ǰ����и��ʱ�Ϊ�Ѹ���
						double random1=Math.random();
						if(random1>=0&&random1<1.0/k){  //1/k�ĸ��ʲ��ٴ�������Ϣ����Ϊ�Ѹ���
							nodes2[i].setisolated(true);
						}
				}else{  //���ѡȡ�Ľ�����ܸ�Ⱦ
					nodes2[random].setInfected(true);
				}
			}
		}
			isolated =0;
			infected=0;
			for(int j=0;j<N;j++){  //ͬ��
				nodes1[j].setInfected(nodes2[j].isInfected());
				nodes1[j].setisolated(nodes2[j].isisolated());
				if(nodes1[j].isisolated()==true) isolated++;
				if(nodes1[j].isInfected()==true) infected++;
			}
			System.out.println("isolated="+isolated);
			System.out.println("infected="+infected);
	}
		//System.out.println("N="+N);
		System.out.println("round="+round);
}
}