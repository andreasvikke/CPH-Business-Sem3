let url = "https://randomuser.me/api/";

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() });
    }
    return res.json();
}

const userFacade = () => {
    const getUsers = (limit) => {
        return fetch(url + `?results=${limit}`).then(handleHttpErrors);
    }

    return {
        getUsers
    }
}
export default userFacade();