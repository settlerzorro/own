/**
 * Created by zym on 2018/3/7.
 */

import java.util.Random;
/**
 * Created by zs on 2018/3/7.
 */
public class TestDemo {

    public void test1(){
        float arr[]= new float[1000000];
        for (int i=0;i<1000000;i++){
            Random random=new Random();
            float v = random.nextFloat() * 50f;
            arr[i]=v;
        }
        long start=System.currentTimeMillis();
        float[] maxNumber = HeapUtils.getMaxNumber(100, arr);
        HeapUtils.heapSort(maxNumber);
        HeapUtils.print(maxNumber);
        System.out.println(System.currentTimeMillis()-start);
    }
    public static void main(String[] args) {
        TestDemo t = new TestDemo();
        t.test1();
    }
}
//执行时间为10ms
