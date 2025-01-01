'use server';

export const handleSignIn = async (prevState: unknown, formData: FormData) => {
  const email = formData.get('email');
  const password = formData.get('password');
  try {
    const response = await fetch('http://localhost:8001/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email,
        password,
      }),
    });
    return await response.json();
  } catch (error) {
    console.error(error);
    return { error };
  }
};

export const handleSignUp = async (formData: FormData) => {
  const email = formData.get('email');
  const password = formData.get('password');
  try {
    const response = await fetch('http://localhost:8001/auth/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email,
        password,
      }),
    });
    return await response.json();
  } catch (error) {
    console.error(error);
    return { error };
  }
};
