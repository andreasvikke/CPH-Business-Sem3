let url = "http://localhost:4000/books";

function makeOptions(method, body) {
    var opts = {
        method: method,
        headers: {
            "Content-type": "application/json"
        }
    };
    if (body) {
        opts.body = JSON.stringify(body);
    }
    return opts;
}

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() });
    }
    return res.json();
}


function BookStore() {
    const getBooks = () => {
        return fetch(url).then(handleHttpErrors);
    }
    const addEditBook = (book) => {
        if (book.id === undefined)
            return fetch(url, makeOptions("POST", book)).then(handleHttpErrors);
        else
            return fetch(`${url}/${book.id}`, makeOptions("PUT", book)).then(handleHttpErrors);
    }
    const deleteBook = (id) => {
        return fetch(`${url}/${id}`, makeOptions("DELETE")).then(handleHttpErrors);
    }

    return {
        getBooks,
        addEditBook,
        deleteBook
    }
}
export default BookStore();