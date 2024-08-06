import { Link, useNavigate } from "react-router-dom";

import { Footer } from "@/components/Footer";
import { Header } from "@/components/Header";
import { FeaturedPosts } from "@/components/home/FeaturedPosts";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Padding } from "@/components/ui/padding";
import { useEffect, useState } from "react";
import { authApi, images } from "@/utils/api/constants";
import { format } from "date-fns";
import { authentiCate } from "@/utils/functions/function";
import useSWR from "swr";

export function IndexPage() {
  const navigate = useNavigate();
  useEffect(() => {
    if (!authentiCate()) {
      navigate("/");
    }
  }, []);

  const [posts, setPosts]: any[] = useState([]);

  useSWR(`/posts/all`, async (url) => {
    try {
      const response = await authApi.get(url);
      setPosts(response.data.data);
    } catch (error) {
      console.log(error);
    }
  });

  return (
    <MainLayout>
      <main>
        <div className="flex flex-col justify-end fixed top-0 w-full">
          <Header />
        </div>
        {/* <LogoutModal /> */}
        {posts.length != 0 ? (
          <Padding className="py-8 mt-20 grid gap-28 md:grid-cols-2 grid-cols-1 justify-items-center bg-gradient-to-tr from-sky-100 via-white to-slate-100">
            <Link to={`/p/${posts[0].id}`} className="flex flex-col gap-4">
              <img
                src={images.image1}
                alt="Image"
                className="sm:max-w-[600px] w-full max-h-[500px] rounded-xl"
              />
              <h2 className="text-2xl hover:underline cursor-pointer font-bold text-balance max-w-">
                {posts[0].title}
              </h2>
              <p>
                {posts[0].content.length > 500
                  ? posts[0].content.slice(0, 500) + " .........."
                  : posts[0].content}
              </p>
              <div className="flex gap-2">
                <div>
                  <h2>By</h2>
                </div>
                <p>
                  {posts[0].author != null
                    ? posts[0].author.userName
                    : "No author"}
                  ,
                </p>
                <p>{format(new Date(posts[0].dateTime), "yyyy MMMM ddd")}</p>
              </div>
            </Link>
            <FeaturedPosts posts={posts} />
          </Padding>
        ) : (
          <div className="py-8 mt-20  space-x-4 flex items-center justify-center  w-full p-x-3 max-auto absolute  justify-items-center bg-gradient-to-tr from-sky-100 via-white to-slate-100">
            <p>No posts yet</p>
            <div>
              <button
                onClick={() => navigate(`/admin/new-post`)}
                className="flex border border-blck p-2 rounded flex-row items-center"
              >
                <span className="text-black"> Add Post</span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={4}
                  stroke="currentColor"
                  className="size-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                  />
                </svg>
              </button>
            </div>
          </div>
        )}
        {/* 
        <Padding className="md:py-24 py-6 grid gap-28 md:grid-cols-2 grid-cols-1 justify-items-center ">
          <LatestPosts posts={posts} />
          <PopularPosts posts={posts} />
        </Padding> */}
        <div className="flex flex-col justify-end bg-black fixed bottom-0 w-full">
          <Footer />
        </div>
      </main>
    </MainLayout>
  );
}
