CREATE OR REPLACE FUNCTION calculatesumandmediana()
    RETURNS TABLE(sumint bigint, medianadouble double precision) AS $$

BEGIN
		RETURN QUERY 
		SELECT sum(intnumber), 
				percentile_disc(0.5) within group (order by doublenumber)
		FROM note;
END;

$$ LANGUAGE plpgsql;

SELECT calculatesumandmediana();