import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList2 implements List {

  private final int DEFAULT_SIZE = 10;

  private Object[] objects;
  private int index;

  public MyArrayList2() {
    this.objects = new Object[DEFAULT_SIZE];
    this.index = 0;
  }

  @Override
  public int size() {
    return index;
  }

  @Override
  public boolean isEmpty() {
    return index == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public Iterator iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(objects, index);
  }

  @Override
  public boolean add(Object o) {
    growIfNeeded();
    objects[index] = o;
    index++;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    int i = indexOf(o);
    throwIllegalArgumentExceptionIfNeeded(i);
    remove(i);
    return true;
  }

  @Override
  public boolean addAll(Collection collection) {
    growIfNeeded(collection);
    Object[] newArray = new Object[objects.length];

    System.arraycopy(objects, 0, newArray, 0, index + 1);
    System.arraycopy(collection.toArray(), 0, newArray, index, collection.size());

    objects = newArray;
    index += collection.size();
    return true;
  }

  @Override
  public boolean addAll(int i, Collection collection) {
    growIfNeeded(collection);
    Object[] newArray = new Object[objects.length];

    System.arraycopy(objects, 0, newArray, 0, i);
    System.arraycopy(collection.toArray(), 0, newArray, i, collection.size());
    System.arraycopy(objects, i, newArray, i + collection.size(), index - i);

    objects = newArray;
    index += collection.size();
    return true;
  }

  @Override
  public void clear() {
    index = 0;
  }

  @Override
  public Object get(int i) {
    throwIllegalArgumentExceptionIfNeeded(i);
    return objects[i];
  }

  @Override
  public Object set(int i, Object o) {
    throwIllegalArgumentExceptionIfNeeded(i);
    return objects[i] = o;
  }

  @Override
  public void add(int i, Object o) {
    throwIllegalArgumentExceptionIfNeeded(i);
    growIfNeeded();

    System.arraycopy(objects, i, objects, i + 1, index - i);
    objects[i] = o;
    index++;
  }

  @Override
  public Object remove(int i) {
    throwIllegalArgumentExceptionIfNeeded(i);
    Object elementToRemove = objects[i];

    System.arraycopy(objects, i + 1, objects, i, index - i);

    objects = Arrays.copyOf(objects, objects.length);
    index--;
    return elementToRemove;
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < index; i++) {
      if (o.equals(objects[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    for (int i = index; i > 0; i--) {
      if (o.equals(objects[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public ListIterator listIterator() {
    return null;
  }

  @Override
  public ListIterator listIterator(int i) {
    return null;
  }

  @Override
  public List subList(int i, int i1) {
    throwIllegalArgumentExceptionIfNeeded(i);
    throwIllegalArgumentExceptionIfNeeded(i1);

    Object[] newArray = new Object[i1 - i];
    System.arraycopy(objects, i, newArray, 0, i1 - i);

    return List.of(newArray);
  }

  @Override
  public boolean retainAll(Collection collection) {
    throwIllegalArgumentExceptionIfNeeded(collection);

    Object[] newArray = new Object[collection.size()];
    int i = 0;
    for (int i2 = 0; i2 < index; i2++) {
      System.out.println(objects[i2]);
      for (Object o : collection) {
        if (objects[i2].equals(o)) {
          newArray[i] = o;
          i++;
        }
      }
    }
    objects = Arrays.copyOf(newArray, objects.length);
    index = collection.size();
    return true;
  }

  @Override
  public boolean removeAll(Collection collection) {
    throwIllegalArgumentExceptionIfNeeded(collection);
    for (Object o : collection) {
      remove(o);
    }
    return true;
  }

  @Override
  public boolean containsAll(Collection collection) {
    for (Object o : collection) {
      if (!contains(o)) {
        return false;
      }
    }
    return true;
  }


  //what the difference?
  @Override
  public Object[] toArray(Object[] objects) {
    return Arrays.copyOf(objects, objects.length);
  }

  private void growIfNeeded() {
    if (index + 1 >= objects.length) {
      objects = Arrays.copyOf(objects, objects.length * 2);
    }
  }

  private void growIfNeeded(Collection collection) {
    if (index + 1 + collection.size() >= objects.length) {
      objects = Arrays.copyOf(objects, objects.length + collection.size());
    }
  }

  private void throwIllegalArgumentExceptionIfNeeded(int index) {
    if (index < 0 || index >= this.index) {
      throw new IndexOutOfBoundsException("The " + index + " index is out of bounds");
    }
  }

  private void throwIllegalArgumentExceptionIfNeeded(Collection collection) {
    for (Object o : collection) {
      if (!contains(o)) {
        throw new IllegalArgumentException("The List doesn't have " + o + " element in collection");
      }
    }
  }
}
