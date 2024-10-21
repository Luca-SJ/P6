export interface News {
	id: number,
	description: string,
	title: string,
    owner_id: number,
    picture: string,
	created_at: Date,
	updated_at: Date
}