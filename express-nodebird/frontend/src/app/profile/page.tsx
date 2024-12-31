export default async function Page() {
  try {
    const response = await fetch('http://localhost:8001/user/1');
    const data = await response.json();
    console.log(data);
  } catch (err) {
    console.log(err);
  }
  return <div></div>;
}
