module.exports = function (req, res, next) {
    // Converts POST to GET and move payload to query params
    // This way it will make JSON Server that it's GET request
    req.method = 'GET'
    // req.query = req.body
    if (req.method === 'DELETE') {

    }
  // Continue to JSON Server router
  next()
}
