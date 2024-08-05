import { useNavigate } from "react-router-dom";

export default function AddPost() {
  const navigate = useNavigate();
  return (
    <div>
      <div className="w-full  justify-end items-end flex">
        <button
          onClick={() => navigate(`/admin/new-post`)}
          className="flex border border-black p-2 rounded flex-row items-center"
        >
          <span> Add Post</span>
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
  );
}
