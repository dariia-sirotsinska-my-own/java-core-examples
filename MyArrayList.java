import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList implements List {

  private Object[] objectsArray;
  private int size;

  MyArrayList() {
    objectsArray = new Object[0];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    for (Object object : objectsArray) {
      if (o.equals(object)) {
        return true;
      }
    }
    return false;
  }

  //Not needed to be implemented
  @Override
  public Iterator iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(this.objectsArray, this.size);
  }

  @Override
  public boolean add(Object o) {
    Object[] newArray = Arrays.copyOf(this.objectsArray, size + 1);

    newArray[this.size] = o;
    size++;

    objectsArray = newArray;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    if (contains(o)) {

      Object[] newArray = new Object[size - 1];

      int index = 0;

      while (!o.equals(objectsArray[index])) {
        newArray[index] = objectsArray[index];
        index++;
      }
      index++;

      for (; index <= newArray.length; index++) {
        newArray[index - 1] = objectsArray[index];
      }
      size--;
      objectsArray = newArray;
      return true;
    } else
      return false;
  }

  @Override
  public boolean addAll(Collection collection) {

    Object[] newArray = Arrays.copyOf(this.objectsArray, this.size + collection.size());

    int index = this.size;
    for (Object object : collection) {
      newArray[index] = object;
      index++;
      size++;
    }
    objectsArray = newArray;
    return true;
  }


  @Override
  public void add(int i, Object o) {

    throwExceptionIfOutBounds(i);

    int newSize = this.size + 1;
    Object[] newArray = new Object[newSize];

    int index = 0;

    while (index < i) {
      newArray[index] = objectsArray[index];
      index++;
    }

    newArray[i] = o;

    while (index < size) {
      newArray[index + 1] = objectsArray[index];
      index++;
    }

    objectsArray = newArray;
    size = newSize;
  }

  @Override
  public boolean addAll(int i, Collection collection) {

    throwExceptionIfOutBounds(i);

    int newSize = this.size + collection.size();
    Object[] newArray = new Object[newSize];

    int index = 0;

    while (index < i) {
      newArray[index] = objectsArray[index];
      index++;
    }

    for (Object object : collection) {
      newArray[i] = object;
      i++;
    }

    while (index < size) {
      newArray[index + collection.size()] = objectsArray[index];
      index++;
    }

    objectsArray = newArray;
    size = newSize;

    return true;
  }

  @Override
  public void clear() {
    this.size = 0;
    this.objectsArray = new Object[0];
  }

  @Override
  public Object get(int i) {
    return objectsArray[i];
  }

  @Override
  public Object set(int i, Object o) {
    objectsArray[i] = o;
    return o;
  }


  @Override
  public Object remove(int i) {

    throwExceptionIfOutBounds(i);

    Object o = get(i);
    remove(o);
    return o;
  }


  @Override
  public int indexOf(Object o) {

    int index = 0;
    for (Object object : objectsArray) {
      if (o.equals(object)) {
        return index;
      }
      index++;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {

    Object[] reversedList = Arrays.copyOf(objectsArray, size);
    Collections.reverse(Arrays.asList(reversedList));

    int index = size - 1;

    for (Object object : reversedList) {
      if (o.equals(object)) {
        return index;
      }
      index--;
    }
    return -1;
  }

  //Not needed to be implemented
  @Override
  public ListIterator listIterator() {
    return null;
  }

  //Not needed to be implemented
  @Override
  public ListIterator listIterator(int i) {
    return null;
  }

  //try to implement
  @Override
  public List<Object> subList(int i, int i1) {

    if (i < 0) {
      throwOutOfBoundsException(i);
    }

    if (i1 > size) {
      throwOutOfBoundsException(i);
    }

    if (i > i1) {
      throwIllegalArgumentException();
    }

    var myList = new MyArrayList();
    int index = 0;

    for (Object obj : objectsArray) {
      if (index >= i && index < i1) {
        myList.add(obj);
      }
      index++;
    }
    return List.of(myList.getObjectsArray());
  }

  @Override
  public boolean retainAll(Collection collection) {

    if (containsAll(collection)) {
      Object[] newArray = new Object[collection.size()];
      int index = 0;

      for (Object o : objectsArray) {
        for (Object o2 : collection) {
          if (o2.equals(o)) {
            newArray[index] = o2;
            index++;
          }
        }
      }
      objectsArray = newArray;
      size = collection.size();
      return true;
    }
    return false;
  }

  @Override
  public boolean removeAll(Collection collection) {

    for (Object o : objectsArray) {
      for (Object o2 : collection) {
        if (o2.equals(o)) {
          remove(o2);
        }
      }
    }
    return true;
  }

  @Override
  public boolean containsAll(Collection collection) {
    for (Object ob : collection) {
      if (!contains(ob)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Object[] toArray(Object[] objects) {
    return new Object[0];
  }

  public void setObjectsArray(Object[] objectsArray) {
    this.objectsArray = objectsArray;
  }

  public Object[] getObjectsArray() {
    return objectsArray;
  }

  private void throwOutOfBoundsException(int index) {
    throw new IndexOutOfBoundsException("The " + index + " index is out of bounds");
  }


  private void throwExceptionIfOutBounds(int i) {
    if (i < 0 || i > size()) {
      throw new IndexOutOfBoundsException("The " + i + " index is out of bounds");
    }
  }

  private void throwIllegalArgumentException() {
    throw new IllegalArgumentException();
  }
}
