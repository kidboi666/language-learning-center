'use client';

import { PropsWithChildren } from 'react';

interface Props {
  isPending: boolean;
}

export default function FormSubmitButton({
  children,
  isPending,
}: PropsWithChildren<Props>) {
  return (
    <button
      type="submit"
      disabled={isPending}
      className="rounded-md bg-zinc-800 p-2 font-bold text-white"
    >
      {isPending ? 'loading...' : children}
    </button>
  );
}
