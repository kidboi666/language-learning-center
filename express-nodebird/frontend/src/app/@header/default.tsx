import {
  getSession,
  getViewCount,
  logout,
} from '@/app/actions/auth/auth-action';
import FetchButton from '@/app/@header/_components/fetch-button';

export default async function Default() {
  return (
    <div className="fixed left-0 top-0 flex w-full gap-2 bg-white p-2 shadow-md">
      <FetchButton action={logout}>로그아웃</FetchButton>
      <FetchButton action={getSession}>세션 가져오기</FetchButton>
      <FetchButton action={getViewCount}>view 가져오기</FetchButton>
    </div>
  );
}
