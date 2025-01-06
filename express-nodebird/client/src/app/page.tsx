'use client';

import { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import Image from 'next/image';
import Button from '@/components/Button';

export default function Home() {
  const [image, setImage] = useState<File | null>(null);
  const [preview, setPreview] = useState<string>('');
  const [title, setTitle] = useState<string>('');
  const [content, setContent] = useState<string>('');

  const handleChangeTitle = (event: ChangeEvent<HTMLInputElement>): void => {
    setTitle(event.target.value);
  };

  const handleChangeContent = (event: ChangeEvent<HTMLInputElement>): void => {
    setContent(event.target.value);
  };

  const handleChangeFile = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target?.files?.[0];
    if (file) {
      setImage(file);
      const previewSrc = URL.createObjectURL(file);
      setPreview(previewSrc);
    }
  };

  const handleSubmitImage = async () => {
    let res;
    if (image) {
      const formData = new FormData();
      formData.append('image', image);
      const url = process.env.NEXT_PUBLIC_SERVER_URL + '/post/image';
      res = await fetch(url, {
        method: 'POST',
        credentials: 'include',
        body: formData,
      });
    }
    return res;
  };

  const handleSubmitPost = async (e: FormEvent) => {
    e.preventDefault();
    if (image) {
      const res = await handleSubmitImage();
      const data = await res?.json();
      console.log(data);
      if (data.url) {
        const url = process.env.NEXT_PUBLIC_SERVER_URL + '/post';
        await fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          credentials: 'include',
          body: JSON.stringify({
            content,
            image: data.url,
            title,
          }),
        });
      }
    }
  };

  useEffect(() => {
    return () => {
      if (preview) {
        URL.revokeObjectURL(preview);
      }
    };
  }, []);

  return (
    <form
      onSubmit={handleSubmitPost}
      className="flex w-full max-w-[600px] flex-col gap-2 rounded-md bg-foreground p-4 text-background shadow-md"
    >
      <label htmlFor="title">제목</label>
      <input type="text" value={title} onChange={handleChangeTitle} />
      <label htmlFor="content">내용</label>
      <input type="text" value={content} onChange={handleChangeContent} />
      {preview && (
        <Image src={preview} width={200} height={200} alt={'image preview'} />
      )}
      <input
        type="file"
        onChange={handleChangeFile}
        accept="image/*"
        name="image"
      />
      <Button type="submit">포스트 쓰기</Button>
    </form>
  );
}
