function BookStore() {
    let books = [
        { id: 1,title: "How to Learn JavaScript - Vol 1", info: "Study hard"},
        { id: 2,title: "How to Learn ES6",info: "Complete all exercises :-)"},
        { id: 3,title: "How to Learn React",info: "Complete all your CA's"},
        { id: 4,title: "Learn React", info: "Don't drink beers, until Friday (after four)"}
        ];
    let nextID = 5;

    const getBooks = () => { return books; }
    const addBook = (book) => {
        book.id = ++nextID;
        books.push(book);
    }

    return {
        getBooks,
        addBook
    }
}
export default BookStore();