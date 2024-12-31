export default async function () {
  const response = await fetch('http://localhost:8001/profile')
  const data = await response.json();
  console.log(data)
  return (
      <div></div>
  )
}

