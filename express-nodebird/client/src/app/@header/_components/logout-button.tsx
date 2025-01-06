import Button from '@/components/Button';

export default function LogoutButton() {
  const handleLogout = async () => {
    await fetch(`${process.env.NEXT_PUBLIC_SERVER_URL}/auth/logout`, {
      method: 'POST',
      credentials: 'include',
    });
  };
  return <Button onClick={handleLogout}>로그아웃</Button>;
}
