let loadBooksBtn = document.getElementById('loadBooks')

loadBooksBtn.addEventListener('click', onLoadBooks)

function onLoadBooks(event) {
    const requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let authorContainer = document.getElementById('authors-container')
    authorContainer.innerHTML = ''

    fetch("http://localhost:8080/api/books/", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(book => {
            let row = document.createElement('tr')

            let titleCol =document.createElement('td')
            let authorCol = document.createElement('td')
            let isbnCol = document.createElement('td')
            let actionCol = document.createElement('td')

            titleCol.textContent = book.title
            authorCol.textContent= book.author.name
            isbnCol.textContent = book.isbn

            row.appendChild(titleCol)
            row.appendChild(authorCol)
            row.appendChild(actionCol)
            row.appendChild(isbnCol)

            authorContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));

}