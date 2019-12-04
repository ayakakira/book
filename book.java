interface Iterator {
public void first();
public void next();
public boolean isDone();
public Object getItem();
}

class BookListIterator implements Iterator {
private BookListAggregate aggregate;
private int current;
public BookListIterator(BookListAggregate aggregate) {
this.aggregate = aggregate;
}
@Override
public void first() {
current = 0;
}
@Override
public void next() {
current += 1;
}
@Override
public boolean isDone() {
if (current >= aggregate.getNumberOfStock()) {
return true;
}
else {
return false;
}
}
@Override
public Object getItem() {
return aggregate.getAt(current);
}
}

interface Aggregate {
public Iterator createIterator();
}

class BookListAggregate implements Aggregate {
private Book[] list = new Book[20];
private int numberOfStock;
@Override
public Iterator createIterator() {
return new BookListIterator(this);
}
public void add(Book book) {
list[numberOfStock] = book;
numberOfStock += 1;
}
public Object getAt(int number) {
return list[number];
}
public int getNumberOfStock() {
return numberOfStock;
}
}

public class IteratorSample1 {
public static void main(String[] args) {
BookListAggregate bookListAggregate = new BookListAggregate();
Iterator iterator = bookListAggregate.createIterator();
bookListAggregate.add(new Book("動物図鑑", 3700));
bookListAggregate.add(new Book("くらべる図鑑", 7300));
bookListAggregate.add(new Book("昆虫図鑑", 5400));
bookListAggregate.add(new Book("海の生物図鑑", 5200));
iterator.first(); // まず探す場所を先頭位置にしてもらう
while ( ! iterator.isDone() ) { // まだある？ まだあるよ！
Book book = (Book)iterator.getItem(); // はいどうぞ (と受取る)
System.out.println(book.getName());
iterator.next(); // 次を頂戴
}
}
}