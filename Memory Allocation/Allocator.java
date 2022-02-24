import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Allocator {


    public static void main(String[] args)throws FileNotFoundException, IOException,NumberFormatException {
        //Yasin ORTA 190709059
        ArrayList<String> memory=new ArrayList<>();//for first-fit
        ArrayList<String> process=new ArrayList<>();//for first-fit
        ArrayList<String> memory1=new ArrayList<>();//for best-fit
        ArrayList<String> process1=new ArrayList<>();//for best-fit
        ArrayList<String> memory2=new ArrayList<>();//for worst-fit
        ArrayList<String> process2=new ArrayList<>();//for worst-fit

        String temp4;//adding memory
        String temp5;//adding process
        int counter_line=1;
        File inp = new File("memory.txt");
        File out = new File("output.txt");
        Scanner content = new Scanner(inp);
        FileWriter write = new FileWriter("output.txt");

        while (content.hasNextLine()) {
            String line = content.nextLine();
            if (line.isEmpty()) continue;
            String[] lines = line.split("\\s*,\\s*");
            //adding memory
            if(counter_line==1) {
                for(int i=0;i<lines.length;i++) {
                    temp4 = String.valueOf(lines[i]);
                    memory.add(temp4);
                }
            }
            //adding process
            if(counter_line==2){
                for(int i=0;i<lines.length;i++) {
                    temp5 = String.valueOf(lines[i]);
                    process.add(temp5);
                }
            }
            counter_line++;
        }
        memory1.addAll(memory);//for best-fit
        memory2.addAll(memory);//for best-fit
        process1.addAll(process);//for worst-fit
        process2.addAll(process);//for worst-fit
        int c=0;
        int temp;//memory-process int
        String temp2;//memory-process string
        String temp3;//memory-process string with *
        int m=memory.size();
        int n= process.size();
        int w=0;//counter for no memory allocation
        int allocation_ff[]=new int[n];
        int allocation_bf[]=new int[process1.size()];
        int allocation_wf[]=new int[process2.size()];
        for(int i=0;i<allocation_ff.length;i++)
            allocation_ff[i]=-1;
        for(int i=0;i<allocation_bf.length;i++)
            allocation_bf[i]=-1;
        for(int i=0;i<allocation_wf.length;i++)
            allocation_wf[i]=-1;

        write.write("First-Fit Memory Allocation");//write
        write.write("\n"+"-------------------------------------------------");//write
        write.write("\n"+"start =>");//write


        for(int i=0;i< memory.size();i++)
            write.write(" "+memory.get(i));//write

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(j==memory.size()-1 && allocation_ff[i] == -1){
                    write.write("\n" + "  " + process2.get(c) + " => ");//write
                    write.write("Not allocated, must wait.");//write
                    c++;
                }
                if(memory.get(j).contains("*"))
                    continue;
                if(Integer.parseInt(memory.get(j))  >= Integer.parseInt(process.get(i))){
                    allocation_ff[i]=j;
                    temp=Integer.parseInt(memory.get(j))-Integer.parseInt(process.get(i));
                    {
                        temp2 = String.valueOf(temp);
                        memory.set(j, temp2);
                        temp3 = (String.valueOf(process.get(i)) + "*");
                        memory.add(j, temp3);
                        write.write("\n" + "  " + process1.get(c) + " => ");//write
                        c++;
                        m++;
                    }
                    for(int d=0;d< memory.size();d++){
                        if(memory.get(d).contains("*"))
                            write.write(memory.get(d)+" ");//write
                        else if(Integer.parseInt(memory.get(d)) !=0 )
                            write.write(Integer.parseInt(memory.get(d))+" ");//write
                        if(d== memory.size())
                            d=0;
                    }
                    break;
                }
            }
        }
        write.write("\n");//write
        write.write("\n"+"Best-Fit Memory Allocation");//write
        write.write("\n"+"-------------------------------------------------");//write
        write.write("\n"+"start =>");//write
        for(int i=0;i< memory1.size();i++)
            write.write(" "+memory1.get(i));//write

        int temp6;//memory1-process1
        String temp7;//memory1-process1 string
        String temp8;//memory1-process1 string with *
        int f=0;//counter for =>
        for(int i=0;i< process1.size();i++){
            int bt=-1;
            for(int j=0;j< memory1.size();j++){
                if(memory1.get(j).contains("*"))
                    continue;
                if(Integer.parseInt(memory1.get(j)) >= Integer.parseInt(process1.get(i))){
                    if(bt==-1)
                        bt=j;
                    else if(Integer.parseInt(memory1.get(bt)) > Integer.parseInt(memory1.get(j)))
                        bt=j;
                }
            }
            if(bt == -1){
                write.write("\n" + "  " + process2.get(f) + " => ");//write
                write.write("Not allocated, must wait.");//write
                f++;
            }
            if(bt!=-1){
                allocation_bf[i]=bt;
                temp6=Integer.parseInt(memory1.get(bt))-Integer.parseInt(process1.get(i));
                {
                    temp7 = String.valueOf(temp6);
                    memory1.set(bt, temp7);
                    temp8 = (String.valueOf(process1.get(i)) + "* ");
                    memory1.add(bt, temp8);
                    write.write("\n" + "  " + process1.get(f) + " => ");//write
                }

                for(int g=0;g< memory1.size();g++){
                    if(memory1.get(g).contains("*"))
                        write.write(memory1.get(g));//write
                    else if(Integer.parseInt(memory1.get(g)) != 0)
                        write.write(Integer.parseInt(memory1.get(g))+" ");//write
                    if(g == memory1.size())
                        g=0;
                }
                f++;
            }
        }
        write.write("\n");//write
        write.write("\n"+"Worst-Fit Memory Allocation");//write
        write.write("\n"+"-------------------------------------------------");//write
        write.write("\n"+"start =>");//write
        for(int i=0;i< memory2.size();i++)
            write.write(" "+memory2.get(i));//write

        int temp0;//memory1-process1
        String temp11;//memory1-process1 string
        String temp12;//memory1-process1 string with *
        int h=0;//counter for =>

        for(int i=0;i< process2.size();i++){
            int wt=-1;
            for(int j=0;j< memory2.size();j++){
                if(memory2.get(j).contains("*"))
                    continue;
                if(Integer.parseInt(memory2.get(j)) >= Integer.parseInt(process2.get(i))){
                    if(wt==-1)
                        wt=j;
                    else if(Integer.parseInt(memory2.get(wt)) < Integer.parseInt(memory2.get(j)))
                        wt=j;
                }
            }
            if(wt == -1){
                write.write("\n" + "  " + process2.get(h) + " => ");//write
                write.write("Not allocated, must wait.");//write
                h++;
            }
            if(wt!=-1){
                allocation_wf[i]=wt;
                temp0=Integer.parseInt(memory2.get(wt))-Integer.parseInt(process2.get(i));
                if(temp0==0)
                    continue;
                else {
                    temp11 = String.valueOf(temp0);
                    memory2.set(wt, temp11);
                    temp12 = (String.valueOf(process2.get(i)) + "* ");
                    memory2.add(wt, temp12);
                    write.write("\n" + "  " + process2.get(h) + " => ");//write
                }

                for(int x=0;x< memory2.size();x++){
                    if(memory2.get(x).contains("*"))
                        write.write(memory2.get(x));//write
                    else if(Integer.parseInt(memory2.get(x)) != 0)
                        write.write(Integer.parseInt(memory2.get(x))+" ");//write
                    if(x== memory2.size())
                        x=0;
                }
                h++;
            }
        }
        write.close();
    }
}
