let URL = "https://countries-project.herokuapp.com";

function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res;
}

const countryFacade = () => {

  const getLabels = () => {
    return fetch(URL + "/labels").then(handleHttpErrors);
  }

  const getCountries = (url) => {
    if(url === "")
      return fetch(URL + "/countries?_page=1" + "&_limit=4").then(handleHttpErrors).then(handleHttpErrors);
    else
      return fetch(url).then(handleHttpErrors).then(handleHttpErrors);
  }
  return {
    getLabels,
    getCountries
  }
}

export default  countryFacade();