import { ComponentProps, PropsWithChildren } from 'react';

type Props = ComponentProps<'button'> & {
  loading?: boolean;
};

export default function Button({
  children,
  onClick,
  loading,
}: PropsWithChildren<Props>) {
  return (
    <button
      className="rounded-md bg-zinc-800 p-2 font-bold text-white"
      type="button"
      onClick={onClick}
    >
      {loading ? 'Loading...' : children}
    </button>
  );
}
