import React, { useState } from 'react';
import {BrowserRouter as Router, Route, NavLink, Switch, Prompt, Link, useRouteMatch} from "react-router-dom"
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
          <Product books={BookStore.getBooks()} />
        </Route>
        <Route exact path="/products/:id">
          <ProductDetail books={BookStore.getBooks()} />
        </Route>
        <Route path="/company">
          <Company />
        </Route>
        <Route path="/addbook">
          <AddBook BookStore={BookStore} />
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

function Product({ books }) {
  let match = useRouteMatch();
  return (
    <div>
      <h3>Product</h3>
      <ul>
        {books.map((book, index) => (
          <li key={index}>{book.title} 
          (<Link to={`${match.url}/${book.id}`}>Details</Link>, )</li>
        ))}
      </ul>
    </div>
  )
}

function ProductDetail({ books }) {
  let match = useRouteMatch();
  let book = books.find((book) => book.id == match.params.id);

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

function AddBook({ BookStore }) {
  const [book, setBook] = useState({});
  const [isBlocking, setIsBlocking] = useState(false);

  const onChange = (evt) => {
    setIsBlocking(true);
    setBook({...book, [evt.target.name]: evt.target.value});
  }
  const onSubmit = (evt) => {
    evt.preventDefault();
    BookStore.addBook(book);
  }

  return (
    <div>
      <h3>Add Book</h3>
      <Prompt
        when={isBlocking}
        message={location =>
          `You have unsaved Changes are you sure you want to go to ${location.pathname}?`
        }
      />
      <form onSubmit={onSubmit}>
        <input placeholder="Title"
               name="title"
               onChange={onChange} />
        <input placeholder="Info"
               name="info"
               onChange={onChange} />
        <input type="submit" value="Submit" />
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
