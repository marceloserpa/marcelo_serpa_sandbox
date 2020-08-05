const express = require('express');
const app = express();

const port = 3000;
 
app.get('/book/v1/books/', (req, res) =>  {
    const books = [
        { author: 'Stephen King', title: 'The Shining' },
        { author: 'Stephen King', title: 'Carrie' }
    ];
    res.send(books);
})
 
app.listen(port, () => console.log(`Listen port = ${port}`));