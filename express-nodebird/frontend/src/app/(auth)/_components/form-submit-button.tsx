'use client';

import { useFormStatus } from 'react-dom';
import { PropsWithChildren } from 'react';

export default function FormSubmitButton({ children }: PropsWithChildren) {
  const status = useFormStatus();
  return (
    <button
      type="submit"
      disabled={status.pending}
      className="rounded-md bg-zinc-800 p-2 font-bold text-white"
    >
      {status.pending ? 'loading...' : children}
    </button>
  );
}
