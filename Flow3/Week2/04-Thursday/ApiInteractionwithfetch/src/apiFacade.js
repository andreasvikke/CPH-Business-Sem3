
let URL = "http://localhost:3456/api";

//The two methods below, are the utility-methods introduced here (use them if you like):
//https://docs.google.com/document/d/1hF9P65v_AJKCjol_gFkm3oZ1eVTuOKc15V6pcb3iFa8/edit?usp=sharing

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

function apiFacade() {
  //OBSERVE This returns a promise, NOT the actual data, you must handle asynchronicity by the client
  function getPersons() {
    return fetch(URL).then(handleHttpErrors);
  }

  function addEditPerson(person) {
    if(person.id === "")
      return fetch(URL, makeOptions("POST", person)).then(handleHttpErrors);
    else
      return fetch(URL + "/" + person.id, makeOptions("PUT", person)).then(handleHttpErrors);
  }

  function deletePerson(id) {
    return fetch(URL + "/" + id, makeOptions("DELETE", null)).then(handleHttpErrors);
  }
  
  return {
    getPersons,
    addEditPerson,
    deletePerson
  };
}

export default apiFacade();

