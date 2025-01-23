const express = require('express');
const { searchGoogle } = require('../services/search-google');
const router = express.Router();

/* GET home page. */
router.get('/', async (req, res, next) => {
  const start = parseInt(req.query.start, 10) || 1;
  const nextStart = start + 10;
  const result = await searchGoogle('서울 원룸 자취방', start);
  res.render('index', { title: 'Express', result, start, nextStart });
});

module.exports = router;
