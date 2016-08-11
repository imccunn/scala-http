var express = require('express')
var app = express();

app.set('port', 3333);

var router = express.Router();
app.use(function(req, res, next) {
  var data = '';
  req.on('data', (c) => { data += c; });
  req.on('end', () => {
    req.body = JSON.parse(data);
    next();
  })
})

router.get('/data', (req, res) => {
  res.status(200).send({wow: 33})
});

router.post('/data', (req, res) => {
  console.log('body: ', req.body);
  res.status(200).send('data received.');
})
app.use(router);
app.listen(3333, (err, data) => {
  console.log(`err: ${err}, data: ${data}`);
  console.log('Listening on port: ', app.get('port'));
})
