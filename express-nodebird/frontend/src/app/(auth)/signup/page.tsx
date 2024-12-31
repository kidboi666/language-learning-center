import FormSubmit from '@/app/(auth)/signup/_components/form-submit';

export default function Page() {
  const handleSubmit = async (formData: FormData) => {
    'use server';

    const email = formData.get('email');
    const password = formData.get('password');
    try {
      await fetch('http://localhost:8001/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email,
          password,
        }),
      });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form
      action={handleSubmit}
      className="flex h-full w-96 flex-col gap-4 rounded-lg bg-white p-4 shadow-lg"
    >
      <h1 className="text-2xl font-bold text-zinc-800">회원가입</h1>
      <div className="flex w-full flex-col gap-2">
        <label htmlFor="email" className="text-zinc-800">
          이메일
        </label>
        <input
          id="email"
          name="email"
          type="email"
          placeholder="이메일을 입력해주세요."
          className="w-full rounded-md bg-zinc-200 p-2 text-zinc-700 outline-0 ring-zinc-600 transition focus:ring-2"
        />
      </div>
      <div className="flex w-full flex-col gap-2">
        <label htmlFor="password" className="text-zinc-800">
          비밀번호
        </label>
        <input
          id="password"
          name="password"
          type="password"
          placeholder="비밀번호를 입력해주세요."
          className="w-full rounded-md bg-zinc-200 p-2 text-zinc-700 outline-0 ring-zinc-600 transition focus:ring-2"
        />
      </div>
      <FormSubmit />
    </form>
  );
}
