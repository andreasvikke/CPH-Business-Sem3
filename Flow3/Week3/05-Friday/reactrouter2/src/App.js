import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, NavLink, Redirect, Switch, Prompt, Link, useRouteMatch } from "react-router-dom"
import './App.css';

function App({ BookStore }) {
  return (
    <Router>
      <Header />
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route exact path="/products">
          <Product BookStore={BookStore} />
        </Route>
        <Route exact path="/products/:id">
          <ProductDetail BookStore={BookStore} />
        </Route>
        <Route path="/company">
          <Company />
        </Route>
        <Route path="/addbook">
          <AddEditBook BookStore={BookStore} />
        </Route>
        <Route path="/editbook/:id">
          <AddEditBook BookStore={BookStore} />
        </Route>
        <Route path="/deletebook/:id">
          <DeleteBook BookStore={BookStore} />
        </Route>
        <Route path="*">
          <NoMatch />
        </Route>
      </Switch>
    </Router>
  );
}

function Header() {
  return (
    <ul className="header">
      <li><NavLink exact activeClassName="active" to="/">Home</NavLink></li>
      <li><NavLink activeClassName="active" to="/products">Products</NavLink></li>
      <li><NavLink activeClassName="active" to="/addbook">Add Book</NavLink></li>
      <li><NavLink activeClassName="active" to="/company">Company</NavLink></li>
    </ul>
  )
}

function Home() {
  return (
    <div>
      <h3>Home</h3>
    </div>
  )
}

function DeleteBook({ BookStore }) {
  let match = useRouteMatch();
  BookStore.deleteBook(match.params.id);

  return (
    <Redirect to="/products" />
  )
}

function Product({ BookStore }) {
  let match = useRouteMatch();
  const [books, setBooks] = useState([]);

  useEffect(() => {
    BookStore.getBooks().then(data => setBooks(data));
  }, [])

  return (
    <div>
      <h3>Product</h3>
      <ul>
        {books.map((book, index) => (
          <li key={index}>{book.title}
            (<Link to={`${match.url}/${book.id}`}>Details</Link>,  <Link to={`/editbook/${book.id}`}>Edit</Link>, <Link to={`/deletebook/${book.id}`}>Delete</Link>)</li>
        ))}
      </ul>
    </div>
  )
}

function ProductDetail({ BookStore }) {
  let match = useRouteMatch();
  const [book, setBook] = useState({});

  useEffect(() => {
    BookStore.getBooks().then(data => setBook(data.find((book) => book.id == match.params.id)));
  }, [])

  return (
    <div>
      <h3>Product {book.id}</h3>
      <ul>
        <li><b>Title: </b>{book.title}</li>
        <li><b>Id: </b>{book.id}</li>
        <li><b>Info: </b>{book.info}</li>
      </ul>
    </div>
  )
}

function AddEditBook({ BookStore }) {
  let match = useRouteMatch();

  const [book, setBook] = useState({title: "", info: ""});
  const [isBlocking, setIsBlocking] = useState(false);

  useEffect(() => {
    BookStore.getBooks().then(data => setBook(data.find((b) => b.id == match.params.id) || {title: "", info: ""}));
  }, [])

  const onChange = (evt) => {
    setIsBlocking(true);
    setBook({ ...book, [evt.target.name]: evt.target.value });
  }
  const onSubmit = (evt) => {
    evt.preventDefault();
    setIsBlocking(false);
    BookStore.addEditBook(book);
  }

  return (
    <div>
      <h3>{book.title !== "" ? "Edit Book" : "Add Book"}</h3>
      <Prompt
        when={isBlocking}
        message={location =>
          `You have unsaved Changes are you sure you want to go to ${location.pathname}?`
        }
      />
      <form onSubmit={onSubmit}>
        <input placeholder="Title"
          name="title"
          onChange={onChange}
          value={book.title} />
        <input placeholder="Info"
          name="info"
          onChange={onChange}
          value={book.info} />
        <input type="submit" value={book.title !== "" ? "Save Book" : "Add Book"} />
      </form>
    </div>
  )
}

function Company() {
  return (
    <div>
      <h3>Company</h3>
    </div>
  )
}

function NoMatch() {
  return (
    <div>
      <h3>No Match</h3>
    </div>
  )
}

export default App;
