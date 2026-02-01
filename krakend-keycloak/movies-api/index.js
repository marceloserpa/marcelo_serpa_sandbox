const express = require('express');
const app = express();

const port = 3000;
 
app.get('/movie/v1/movies/', (req, res) =>  {
    const movies = [
        { title: 'End Game'}
    ];
    res.send(movies);
})
 
app.listen(port, () => console.log(`Listen port = ${port}`));