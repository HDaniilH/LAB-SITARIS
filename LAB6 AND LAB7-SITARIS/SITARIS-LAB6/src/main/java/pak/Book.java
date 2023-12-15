package pak;

public class Book {
    private String author;
    private String title;
    private String genre;
    private float price;
    private String publish_date;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public float getPrice() {
        return price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public Book(String author, String title, String genre, float price, String publish_date) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
    }


    @Override
    public String toString() {
        return "pak.Book:" +
                "author='" + author + '\'' + '\n' +
                "title='" + title + '\'' + '\n' +
                "genre='" + genre + '\'' + '\n' +
                "price=" + price + '\n' +
                "publish_date='" + publish_date + '\'' +
                '\n';
    }
}