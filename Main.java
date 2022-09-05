import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {

    MyArrayList2 list = new MyArrayList2();

    System.out.println(list.add("1"));
    System.out.println(list.add("2"));
    System.out.println(list.add("3"));
    list.retainAll(List.of("3", "2"));

    //   System.out.println(list.remove("3"));
   /* System.out.println(list.addAll(List.of("2","5")));
    System.out.println(list.addAll(2, List.of("3","4")));*/


    System.out.println(Arrays.toString(list.toArray()));


  }
}
