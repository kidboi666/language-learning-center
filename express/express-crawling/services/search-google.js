const axios = require('axios');

const API_KEY = 'AIzaSyBo9e8Sc4xu8ZFn13D_dsPTmx7hBZJa5SA'; // Google Cloud에서 발급받은 API 키
const CX = 'e70c611e7aa814446'; // Custom Search Engine의 cx 값

async function searchGoogle(query, start) {
  const url = `https://www.googleapis.com/customsearch/v1?q=${encodeURIComponent(
    query,
  )}&key=${API_KEY}&cx=${CX}&num=10&start=${start}`;

  try {
    const response = await axios.get(url);
    const items = response.data.items || [];

    const results = items.map((item) => ({
      title: item.title,
      link: item.link,
      snippet: item.snippet,
    }));

    console.log('검색 결과:', results);
    return results;
  } catch (error) {
    console.error('에러 발생:', error.response?.data || error.message);
  }
}

module.exports = { searchGoogle };
