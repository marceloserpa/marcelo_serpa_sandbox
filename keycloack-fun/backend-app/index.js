const express = require('express')
const cors = require('cors')
const app = express()
const port = 3000

// Middleware
app.use(cors()) // Enable CORS for frontend requests
app.use(express.json()) // Parse JSON request bodies

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.post('/auth/callback', (req, res) => {


    // 1. Store tokens in database/session
    // 2. Validate tokens with Keycloak
    // 3. Create your own session/JWT
    // 4. Log user activity

    console.log(req.body.accessToken)
    console.log(req.body.idToken)
    
})

app.listen(port, () => {
  console.log(`Backend server listening on port ${port}`)
})
